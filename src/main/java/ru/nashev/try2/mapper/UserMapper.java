package ru.nashev.try2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.nashev.try2.dao.CountryRepository;
import ru.nashev.try2.dao.DocRepository;
import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;
import ru.nashev.try2.model.User;

/**
 * Объявление для генерации через mapstruct маппера пользователя на DTO и обратно
 * @author Nashev
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                DocRepository.class,
                CountryRepository.class
        }
)
public interface UserMapper extends GenericMultiMapper<User, UserGetDTO, UserListDTO, UserAddDTO, UserUpdateDTO> {
    @Mapping(target = "docName", source = "userDoc.docName")
    @Mapping(target = "docNumber", source = "userDoc.docNumber")
    @Mapping(target = "docDate", source = "userDoc.docDate", dateFormat = "dd.MM.yy")
    @Mapping(target = "citizenshipName", source = "citizenship.name")
    @Mapping(target = "citizenshipCode", source = "citizenship.code")
    UserGetDTO get(User entity);

    @Mapping(target = "userDoc.doc", source = "docCode")
    @Mapping(target = "userDoc.docName", source = "docName")
    @Mapping(target = "userDoc.docNumber", source = "docNumber")
    @Mapping(target = "userDoc.docDate", source = "docDate", dateFormat = "dd.MM.yy")
    @Mapping(target = "citizenship", source = "citizenshipCode")
    User add(UserAddDTO dto);

    @Mapping(target = "userDoc.docName", source = "docName")
    @Mapping(target = "userDoc.docNumber", source = "docNumber")
    @Mapping(target = "userDoc.docDate", source = "docDate", dateFormat = "dd.MM.yy")
    @Mapping(target = "citizenship", source = "citizenshipCode")
    void update(@MappingTarget User entity, UserUpdateDTO dto);
}