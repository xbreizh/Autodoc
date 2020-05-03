package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceTypeManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.global.IGenericDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.model.dtos.pieces.PieceTypeDTO;
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
public class PieceTypeManagerImpl extends AbstractGenericManager implements PieceTypeManager {
    private static final Logger LOGGER = Logger.getLogger(PieceTypeManagerImpl.class);
    private static final ModelMapper mapper = new ModelMapper();
    private PieceTypeDao dao;

    public Class getEntityClass() {
        return PieceType.class;
    }

    public Class getDtoClass() {
        return PieceTypeDTO.class;
    }

    @Override
    public IGenericDao getDao() {
        LOGGER.info("getting dao: ");

        return dao;
    }


    @Override
    public PieceType transferInsert(Object obj) {
        checkIfDuplicate(obj);
        return (PieceType) dtoToEntity(obj);
    }


    @Override
    public void checkIfDuplicate(Object dtoToCheck) {
        PieceTypeDTO dto = (PieceTypeDTO) dtoToCheck;
        if (dao.getByName(dto.getName()) != null) throw new InvalidDtoException("that pieceType already exist");

    }

    public PieceType transferUpdate(Object obj) {
        PieceTypeDTO dto = (PieceTypeDTO) obj;
        PieceType pieceType = checkIfExistingPieceType(dto);
        if (dto.getName() != null) pieceType.setName(dto.getName());
        return pieceType;
    }

    public PieceType checkIfExistingPieceType(PieceTypeDTO dto) {
        PieceType pieceType = (PieceType) dao.getById(dto.getId());
        if (pieceType == null) throw new InvalidDtoException("pieceType invalid id: " + dto.getId());
        return pieceType;
    }

    @Override
    public boolean deleteById(int entityId) {
        LOGGER.info("deleting pieceType");
        PieceType pieceType = (PieceType) dao.getById(entityId);
        if(pieceType!=null) {
            pieceType.setName("deleted Item");
            dao.update(pieceType);
        }
        boolean deleteResult = dao.deleteById(entityId);
        LOGGER.info("delete result: "+deleteResult);
        if(deleteResult==false){
            LOGGER.error("renaming item we can't delete");
            LOGGER.info(pieceType);
        }
        return true;
    }


}
