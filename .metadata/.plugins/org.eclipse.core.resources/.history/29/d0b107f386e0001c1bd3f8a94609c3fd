package com.projeto.log.logap.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.projeto.log.logap.domain.model.Ocorrencia;
import com.projeto.log.logap.model.OcorrenciaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}
	

}
