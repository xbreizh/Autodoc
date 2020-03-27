package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public class PieceManagerImpl<T, D> extends AbstractGenericManager implements PieceManager {
    private static final Logger LOGGER = Logger.getLogger(PieceManagerImpl.class);
    private PieceDao pieceDao;
    private ModelMapper mapper;
    private PieceTypeDao pieceTypeDao;

    public PieceManagerImpl(PieceDao pieceDao, PieceTypeDao pieceTypeDao) {
        super(pieceDao);
        this.mapper = new ModelMapper();
        this.pieceDao = pieceDao;
        this.pieceTypeDao = pieceTypeDao;
    }


    @Override
    public PieceDTO entityToDto(Object entity) {
        LOGGER.info("entity received: " + entity);
        PieceDTO dto = mapper.map(entity, PieceDTO.class);
        LOGGER.info("converted into dto " + dto);
        return dto;
    }

    @Override
    public Piece dtoToEntity(Object entity) throws Exception {
        PieceDTO dto = (PieceDTO) entity;
        Piece piece = mapper.map(entity, Piece.class);
        checkDataInsert(dto);
        return piece;
    }

    @Override
    public Piece transferInsert(Object obj) throws InvalidDtoException {
        PieceDTO dto = (PieceDTO) obj;
        String name = dto.getName().toUpperCase();
        if (pieceDao.getByName(name) != null) throw new InvalidDtoException("that piece already exist");
        Piece piece = new Piece();
        piece.setName(name.toUpperCase());
        piece.setBrand(dto.getBrand().toUpperCase());
        piece.setBuyingPrice(dto.getBuyingPrice());
        piece.setSellPrice(dto.getSellPrice());
        checkSellingPriceIsEqualOrHigherBuyingPrice(piece);
        piece.setQuantity(dto.getQuantity());
        transferPieceType(dto, piece);
        LOGGER.info("piece to transfer: " + piece);
        return piece;

    }

    public List<Piece> updateStockAndAddPieces(List<Piece> pieces, List<Piece> pieceFromDb) {

        LOGGER.info("updating stock");
        LOGGER.info(pieces);
        LOGGER.info(pieceFromDb);

        if (pieceFromDb != null) {

            // raises db quantity if removing a new item from the bill
            for (Piece p : pieceFromDb) {
                if (!pieces.contains(p)) {
                    LOGGER.info("adding an item");
                    updateQuantity(p, "+");
                }
                pieceDao.update(p);
            }
        }


        if (pieces != null) {

            // raises db quantity if removing a new item from the bill
            for (Piece p : pieces) {
                if (!pieces.contains(p)) {
                    LOGGER.info("adding an item");
                    updateQuantity(p, "+");
                    //  billPieceList.add(p);
                }
                pieceDao.update(p);
            }
        }

        // removes all items from db quantity according to bill
        for (Piece piece : pieces) {
            LOGGER.info("removing an item: " + piece.getId());
            Piece pieceFromStock = (Piece) pieceDao.getById(piece.getId());
            piece = updateQuantity(pieceFromStock, "-");
        }
        LOGGER.info("billPieceList: " + pieces);
        if (pieces != null || pieces.isEmpty()) {
            return pieceFromDb;
        }
        return pieces;

    }

    public Piece updateQuantity(Piece piece, String sign) {
        LOGGER.info("updating quantity: " + piece);
        int actualQuantity = piece.getQuantity();
        if (sign.equalsIgnoreCase("+")) {
            piece.setQuantity(actualQuantity + 1);
        } else if (sign.equalsIgnoreCase("-")) {
            LOGGER.info("removing");
            piece.setQuantity(actualQuantity - 1);
        }
        LOGGER.info("piece now: " + piece);
        pieceDao.update(piece);
        updateNameAccordingToStock(piece.getId());

        return piece;
    }

    public void updateNameAccordingToStock(int id) {
        Piece piece = (Piece) pieceDao.getById(id);
        int quantity = piece.getQuantity();
        LOGGER.info("updating name according to stock: " + quantity);
        String outOfStock = "OOS ";
        String currentName = piece.getName();
        if (quantity <= 0) {
            if (!piece.getName().startsWith(outOfStock)) {
                piece.setName(outOfStock + currentName);
            }
        }
        if (quantity > 0 && currentName.startsWith(outOfStock)) {
            piece.setName(currentName.substring(4));
        }
        LOGGER.info("new name: " + piece.getName());
        pieceDao.update(piece);
    }

    public void checkSellingPriceIsEqualOrHigherBuyingPrice(Piece piece) {
        if (piece.getSellPrice() < piece.getBuyingPrice())
            throw new InvalidDtoException("the selling price must be equal or higher the buying price");
    }


    private void transferPieceType(PieceDTO dto, Piece piece) throws InvalidDtoException {
        if (dto.getPieceTypeId() != 0) {
            PieceType pieceType = (PieceType) pieceTypeDao.getById(dto.getPieceTypeId());
            if (pieceType == null) throw new InvalidDtoException("invalid pieceType id: " + dto.getPieceTypeId());
            piece.setPieceType(pieceType);
        }
    }


    public Piece transferUpdate(Object obj) throws InvalidDtoException {
        PieceDTO dto = (PieceDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id passed");
        Piece piece = (Piece) pieceDao.getById(id);
        if (piece == null) throw new InvalidDtoException("invalid id: " + id);
        String name = dto.getName();
        String brand = dto.getBrand();
        double buyingPrice = dto.getBuyingPrice();
        double sellPrice = dto.getSellPrice();
        int quantity = dto.getQuantity();
        if (name != null) piece.setName(name.toUpperCase());
        if (brand != null) piece.setBrand(brand);
        if (buyingPrice != 0) piece.setBuyingPrice(buyingPrice);
        if (sellPrice != 0) piece.setSellPrice(sellPrice);
        if (quantity != 0) piece.setQuantity(quantity);
        checkSellingPriceIsEqualOrHigherBuyingPrice(piece);
        transferPieceType(dto, piece);
        LOGGER.info("piece to update: " + piece);
        return piece;
    }
}
