package com.algaworks.socialbooks.domain.DTO;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class GenericDTOConverter {

	private GenericDTOConverter() {

	}

	static final ModelMapper modelMapper = new ModelMapper();

	/**
	 *
	 * @param <S>
	 * @param <D>
	 * @param source      Classe original, geralmente faz parte do Model da
	 *                    aplicação
	 * @param targetClass Classe para ser convertida, geralmente é o DTO
	 * @return
	 */
	public static <S, D> D mapTo(S source, Class<D> targetClass) {
		return modelMapper.map(source, targetClass);
	}

	/**
	 *
	 * @param <S>
	 * @param <T>
	 * @param source      Classe original, geralmente faz parte do Model
	 * @param targetClass Classe para ser convertida, geralmente é o DTO
	 * @return
	 */
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

}
