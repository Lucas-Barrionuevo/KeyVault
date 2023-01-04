package com.keyVault.app.service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.keyVault.app.dto.PasswordDTO;
import com.keyVault.app.entity.Password;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.repository.IconRepository;
import com.keyVault.app.repository.PasswordRepository;
@Service
public class PasswordService {
	@Autowired
	private PasswordRepository passwordRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private IconRepository iconRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PasswordDTO createPassword (PasswordDTO passwordDTO) {
		Password password = mappingEntity(passwordDTO);
		password.setCreatedAt(new Date());
		if(password.getCategory()!=null) {
			password.setCategory(categoryRepository.findById(password.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", password.getCategory().getId())));
		}
		if (password.getIcon() != null) {
			password.setIcon(iconRepository.findById(password.getIcon().getId()).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", password.getIcon().getId())));
		}
		Password newPassword = passwordRepository.save(password);
		
		PasswordDTO responsePassword = mappingDTO(newPassword);
		return responsePassword;
	}
	
	public List<PasswordDTO> findAllPasswordsForUser(int user_id){
		List<Password> AllPasswords = passwordRepository.findByUser_id(user_id);
		List<PasswordDTO> AllResponsePasswords = AllPasswords.stream().map(password -> mappingDTO(password)).collect(Collectors.toList());
		return AllResponsePasswords;
	}
	
	public PasswordDTO findPasswordById(int id) {
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		password.setSeenqty(password.getSeenqty() + 1);
		return mappingDTO(password);
	}
	
	public PasswordDTO updatePassword(PasswordDTO passwordDTO, int id) {
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		
		password.setContent(passwordDTO.getContent());
		password.setUrl(passwordDTO.getUrl());
		password.setName(passwordDTO.getName());
		password.setCategory(passwordDTO.getCategory());
		password.setIcon(passwordDTO.getIcon());
		password.setUserOrMail(passwordDTO.getUserOrMail());
		
		Password updatedPassword = passwordRepository.save(password);
		return mappingDTO(updatedPassword);
	}
	
	public void deletePassword(int id) {
		Password password = passwordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Password", "id", id));
		passwordRepository.delete(password);
	}
	
	public PasswordDTO mappingDTO (Password password) {
		PasswordDTO passwordDTO = modelMapper.map(password, PasswordDTO.class);
		return passwordDTO;
	}
	
	public Password mappingEntity (PasswordDTO passwordDTO) {
		Password password = modelMapper.map(passwordDTO, Password.class);
		return password;
	}
}
