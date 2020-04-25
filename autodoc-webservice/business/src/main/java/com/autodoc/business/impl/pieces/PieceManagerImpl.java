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
    public void checkIfDuplicate(Object dtoToCheck) {
        PieceDTO dto = (PieceDTO) dtoToCheck;
        if (dao.getByName(dto.getName()) != null) throw new InvalidDtoException("that piece already exist");
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
        if (dto.getName() != null) piece.setName(dto.getName());
        if (dto.getBrand() != null) piece.setBrand(dto.getBrand());
        if (dto.getBuyingPrice() != 0) piece.setBuyingPrice(dto.getBuyingPrice());
        if (dto.getSellPrice() != 0) piece.setSellPrice(dto.getSellPrice());
        if (dto.getQuantity() != 0) piece.setQuantity(dto.getQuantity());
        checkSellingPriceIsEqualOrHigherBuyingPrice(piece);
        transferPieceType(dto, piece);
        LOGGER.info("piece to update: " + piece);
        return piece;
    }
}
