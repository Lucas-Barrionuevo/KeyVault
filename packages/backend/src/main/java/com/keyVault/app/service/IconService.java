package com.keyVault.app.service;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keyVault.app.dto.IconDTO;
import com.keyVault.app.entity.Icon;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.IconRepository;
@Service
public class IconService {
	@Autowired
	private IconRepository iconRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public IconDTO createIcon (IconDTO iconDTO) {
		Icon icon = mappingEntity(iconDTO);
		
		Icon newIcon = iconRepository.save(icon);
		
		IconDTO responseIcon = mappingDTO(newIcon);
		return responseIcon;
	}
	
	public List<IconDTO> findAllIcons(){
		List<Icon> AllIcons = iconRepository.findAll();
		List<IconDTO> AllResponseIcons = AllIcons.stream().map(icon -> mappingDTO(icon)).collect(Collectors.toList());
		return AllResponseIcons;
	}
	
	public IconDTO findIconById(int id) {
		Icon icon = iconRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", id));
		return mappingDTO(icon);
	}
	
	public IconDTO updateIcon(IconDTO iconDTO, int id) {
		Icon icon = iconRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", id));
		
		icon.setDomain(iconDTO.getDomain());
		icon.setUrl(iconDTO.getUrl());
		
		Icon updatedIcon = iconRepository.save(icon);
		return mappingDTO(updatedIcon);
	}
	
	public void deleteIcon(int id) {
		Icon icon = iconRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", id));
		iconRepository.delete(icon);
	}
	
	public IconDTO mappingDTO (Icon icon) {
		IconDTO iconDTO = modelMapper.map(icon, IconDTO.class);
		return iconDTO;
	}
	
	public Icon mappingEntity (IconDTO iconDTO) {
		Icon icon = modelMapper.map(iconDTO, Icon.class);
		return icon;
	}
}
