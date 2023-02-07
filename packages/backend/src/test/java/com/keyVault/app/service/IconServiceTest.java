package com.keyVault.app.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyVault.app.dto.IconDTO;
import com.keyVault.app.entity.Icon;
import com.keyVault.app.repository.IconRepository;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class IconServiceTest {
	@Mock
	private ModelMapper modelMapper;
	
    @Mock
    private IconRepository iconRepository;
    
    @Autowired
    private IconRepository iconRepo;
    
    @InjectMocks
    private IconService iconService;

    private Icon icon;
    
    private IconDTO iconDTO;

    @BeforeEach
    void setup(){
    	iconRepo.deleteAll();
    	icon = new Icon("test","test");
    	iconDTO = new IconDTO("test","test");
    }
    
    @Test
    void testSaveIcon(){
        //given
        given(iconRepository.save(icon)).willReturn(icon);
        given(modelMapper.map(icon, IconDTO.class)).willReturn(iconDTO);
    	given(modelMapper.map(iconDTO, Icon.class)).willReturn(icon);
        //when
        IconDTO iconSave = iconService.createIcon(iconDTO);

        //then
        assertThat(iconSave).isNotNull();
    }

    @Test
    void testListIcon(){
        //given
    	given(modelMapper.map(icon, IconDTO.class)).willReturn(iconDTO);
    	Icon icon1 = new Icon("test1","test1");
    	iconRepo.save(icon);
    	iconRepo.save(icon1);
        given(iconRepository.findAll()).willReturn(List.of(icon,icon1));

        //when
        List<IconDTO> icons = iconService.findAllIcons();

        //then
        assertThat(icons).isNotNull();
        assertThat(icons.size()).isEqualTo(2);
    }


    @Test
    void testGetIconForId(){
        //given
    	given(modelMapper.map(icon, IconDTO.class)).willReturn(iconDTO);
    	iconRepo.save(icon);
        given(iconRepository.findById(icon.getId())).willReturn(Optional.of(icon));

        //when
        IconDTO iconSave = iconService.findIconById(icon.getId());

        //then
        assertThat(iconSave).isNotNull();
    }

    @Test
    void testUpdateIcon(){
        //given
    	given(modelMapper.map(icon, IconDTO.class)).willReturn(iconDTO);
        given(iconRepository.save(icon)).willReturn(icon);
        icon.setDomain("test2");
        icon.setUrl("test2");
        iconDTO.setDomain("test2");
        iconDTO.setUrl("test2");
        iconRepo.save(icon);
        given(iconRepository.findById(icon.getId())).willReturn(Optional.of(icon));
        //when
        IconDTO iconUpdate  = iconService.updateIcon(iconDTO, icon.getId());

        //then
        assertThat(iconUpdate.getDomain()).isEqualTo("test2");
        assertThat(iconUpdate.getUrl()).isEqualTo("test2");
    }

    @Test
    void testDeleteIcon(){
    	//given
		int id = icon.getId();
		 willDoNothing().given(iconRepository).delete(icon);
		 given(iconRepository.findById(icon.getId())).willReturn(Optional.of(icon));
		 
		 //when
		 iconService.deleteIcon(id);;
		
		 //then
		 verify(iconRepository,times(1)).delete(icon);
    }
}
