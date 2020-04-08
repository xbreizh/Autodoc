package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.pieces.PieceDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import lombok.Builder;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Builder
public class PieceManagerImpl extends AbstractGenericManager implements PieceManager {
    private static final Logger LOGGER = Logger.getLogger(PieceManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private final PieceDao dao;
    private final PieceTypeDao pieceTypeDao;

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    @Override
    public PieceDTO entityToDto(Object entity) {
        LOGGER.info("entity received: " + entity);
        return mapper.map(entity, PieceDTO.class);
    }

    @Override
    public Piece dtoToEntity(Object entity) {
        return mapper.map(entity, Piece.class);
    }

    @Override
    public Piece transferInsert(Object obj) {
        PieceDTO dto = (PieceDTO) obj;
        Piece piece = dtoToEntity(obj);
        checkInsert(dto, piece);
        LOGGER.info("piece to transfer: " + piece);
        return piece;

    }

    private void checkInsert(PieceDTO dto, Piece piece) {
        checkIfDuplicate(dto);
        checkThatPieceIdIsNotNull(dto);
        checkSellingPriceIsEqualOrHigherBuyingPrice(piece);
    }

    public void checkThatPieceIdIsNotNull(PieceDTO dto) {
        if (dto.getPieceTypeId() == 0) throw new InvalidDtoException("pieceId cannot be null");
    }

    @Override
    public void checkIfDuplicate(Object dtoToCheck)  {
        PieceDTO dto = (PieceDTO) dtoToCheck;
        if (dao.getByName(dto.getName()) != null) throw new InvalidDtoException("that piece already exist");
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
                dao.update(p);
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
                dao.update(p);
            }
        }

      /*  // removes all items from db quantity according to bill
        for (Piece piece : pieces) {
            LOGGER.info("removing an item: " + piece.getId());
            Piece pieceFromStock = (Piece) dao.getById(piece.getId());
            piece = updateQuantity(pieceFromStock, "-");
        }
        LOGGER.info("billPieceList: " + pieces);
        if (pieces != null) return pieceFromDb;
        if (pieces.isEmpty()) return pieceFromDb;*/


        return pieces;

    }

    public Piece updateQuantity(Piece piece, String sign) {
        LOGGER.info("updating quantity: " + piece);
        int actualQuantity = piece.getQuantity();
        if (sign.equalsIgnoreCase("+")) {
            LOGGER.info("adding");
            piece.setQuantity(actualQuantity + 1);
        } else if (sign.equalsIgnoreCase("-")) {
            LOGGER.info("removing");
            piece.setQuantity(actualQuantity - 1);
        }
        LOGGER.info("piece now: " + piece);
        dao.update(piece);
        piece.setName(updateNameAccordingToStock(piece.getId()));

        return piece;
    }

    public String updateNameAccordingToStock(int id) {
        Piece piece = (Piece) dao.getById(id);
        int quantity = piece.getQuantity();
        LOGGER.info("updating name according to stock: " + quantity);
        String outOfStock = "OOS ";
        String currentName = piece.getName();
        if (quantity <= 0 && !piece.getName().startsWith(outOfStock)) {
                return outOfStock + currentName;
            }

        if (quantity > 0 && currentName.startsWith(outOfStock)) {
            return currentName.substring(4);
        }
        LOGGER.info("new name: " + piece.getName());
        return currentName;
    }

    public void checkSellingPriceIsEqualOrHigherBuyingPrice(Piece piece) {
        if (piece.getSellPrice() < piece.getBuyingPrice())
            throw new InvalidDtoException("the selling price must be equal or higher the buying price");
    }


    public void transferPieceType(PieceDTO dto, Piece piece) {
        if (dto.getPieceTypeId() != 0) {
            PieceType pieceType = (PieceType) pieceTypeDao.getById(dto.getPieceTypeId());
            if (pieceType == null) throw new InvalidDtoException("invalid pieceType id: " + dto.getPieceTypeId());
            piece.setPieceType(pieceType);
        }
    }


    public Piece transferUpdate(Object obj) {
        PieceDTO dto = (PieceDTO) obj;
        int id = dto.getId();
        if (id == 0) throw new InvalidDtoException("no id passed");
        Piece piece = (Piece) dao.getById(id);
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
