package com.autodoc.business.impl.pieces;

import com.autodoc.business.contract.pieces.PieceManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.business.impl.AbstractGenericManager;
import com.autodoc.dao.contract.car.CarModelDao;
import com.autodoc.dao.contract.person.provider.ProviderDao;
import com.autodoc.dao.contract.pieces.PieceTypeDao;
import com.autodoc.dao.impl.pieces.PieceDaoImpl;
import com.autodoc.model.dtos.pieces.PieceDTO;
import com.autodoc.model.models.car.CarModel;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.pieces.PieceType;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class PieceManagerImpl<T, D> extends AbstractGenericManager implements PieceManager {
    private static final Logger LOGGER = Logger.getLogger(PieceManagerImpl.class);
    private PieceDaoImpl<Piece> pieceDao;
    private ModelMapper mapper;
    private CarModelDao carModelDao;
    private PieceTypeDao pieceTypeDao;
    private ProviderDao providerDao;

    public PieceManagerImpl(PieceDaoImpl<Piece> pieceDao, CarModelDao carModelDao, PieceTypeDao pieceTypeDao, ProviderDao providerDao) {
        super(pieceDao);
        this.mapper = new ModelMapper();
        this.pieceDao = pieceDao;
        this.carModelDao = carModelDao;
        this.pieceTypeDao = pieceTypeDao;
        this.providerDao = providerDao;
    }


    @Override
    public PieceDTO entityToDto(Object entity) {
        PieceDTO dto = mapper.map(entity, PieceDTO.class);
        LOGGER.info("converted into dto");
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
        piece.setBrand(dto.getBrand());
        piece.setBuyingPrice(dto.getBuyingPrice());
        piece.setSellPrice(dto.getSellPrice());
        piece.setQuantity(dto.getQuantity());
        transferCarModel(dto, piece);
        transferPieceType(dto, piece);
        //transferProvider(dto, piece);
        LOGGER.info("piece to transfer: " + piece);
        return piece;

    }

/*    private void transferProvider(PieceDTO dto, Piece piece) throws Exception {
        if (dto.getProviderId() != 0) {
            Provider provider = (Provider) providerDao.getById(dto.getProviderId());
            if (provider == null) throw new Exception("invalid provider id: " + dto.getProviderId());
            piece.setProvider(provider);
        }
    }*/

    private void transferPieceType(PieceDTO dto, Piece piece) throws InvalidDtoException {
        if (dto.getPieceTypeId() != 0) {
            PieceType pieceType = (PieceType) pieceTypeDao.getById(dto.getPieceTypeId());
            if (pieceType == null) throw new InvalidDtoException("invalid pieceType id: " + dto.getPieceTypeId());
            piece.setPieceType(pieceType);
        }
    }

    private void transferCarModel(PieceDTO dto, Piece piece) throws InvalidDtoException {
        if (dto.getCarModelId() != 0) {
            CarModel carModel = (CarModel) carModelDao.getById(dto.getCarModelId());
            if (carModel == null) throw new InvalidDtoException("invalid carModel id: " + dto.getCarModelId());
            piece.setCarModel(carModel);
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
        if(name!=null)piece.setName(name.toUpperCase());
        if (brand != null) piece.setBrand(brand);
        if (buyingPrice != 0) piece.setBuyingPrice(buyingPrice);
        if (sellPrice != 0) piece.setSellPrice(sellPrice);
        if (quantity != 0) piece.setQuantity(quantity);

        transferCarModel(dto, piece);
        transferPieceType(dto, piece);
      //  transferProvider(dto, piece);
        LOGGER.info("piece to update: " + piece);
        return piece;
    }
}
