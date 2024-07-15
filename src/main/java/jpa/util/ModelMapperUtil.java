package jpa.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtil {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
    }

    /**
     * Object 변환
     * ENTITY -> DTO
     * @param
     * @param outClass
     * @param <D>
     * @param <T>
     * @return
     */
    public static <D, T> D map(final T inClass, Class<D> outClass) {
        return modelMapper.map(inClass, outClass);
    }

    /**
     * 목록 변환
     * ENTITY LIST -> DTO LIST
     * @param entityList
     * @param outCLass
     * @param <D>
     * @param <T>
     * @return
     */
    public static <D, T> List<D> entityListToDto(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
}