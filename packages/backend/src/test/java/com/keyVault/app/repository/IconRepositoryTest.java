package com.keyVault.app.repository;
import static org.assertj.core.api.Assertions.assertThat;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.keyVault.app.entity.Icon;
import com.keyVault.app.exceptions.KeyVaultAppException;

import java.util.List;
import java.util.Optional;
@SpringBootTest
public class IconRepositoryTest {

    @Autowired
    private IconRepository iconRepository;

    private Icon icon;

    @BeforeEach
    void setup(){
    	iconRepository.deleteAll();
    	icon = new Icon("test","test");
    }
    @Test
    void testSaveIcon(){
        //given

        //when
        Icon iconSave = iconRepository.save(icon);

        //then
        assertThat(iconSave).isNotNull();
        assertThat(iconSave.getId()).isGreaterThan(0);
    }
    @Test
    void testListIcons(){
        //given
    	
        Icon icon1 = new Icon("test2","test2");
        iconRepository.save(icon1);
        iconRepository.save(icon);

        //when
        List<Icon> iconList = iconRepository.findAll();

        //then
        assertThat(iconList).isNotNull();
        assertThat(iconList.size()).isEqualTo(2);
    }

    @Test
    void testGetIconForId(){
        iconRepository.save(icon);

        //when
        Icon iconDB = iconRepository.findById(icon.getId()).get();

        //then
        assertThat(iconDB).isNotNull();
    }
    @Test
    void testUpdateIcon(){
        iconRepository.save(icon);

        //when
        Icon iconSave = iconRepository.findById(icon.getId()).get();
        iconSave.setDomain("test2");
        iconSave.setUrl("test2");
        Icon iconUpdate = iconRepository.save(iconSave);

        //then
        assertThat(iconUpdate.getDomain()).isEqualTo("test2");
        assertThat(iconUpdate.getUrl()).isEqualTo("test2");
    }

    @Test
    void testDeleteIcon(){
        iconRepository.save(icon);

        //when
        iconRepository.deleteById(icon.getId());
        Optional<Icon> iconOp = iconRepository.findById(icon.getId());

        //then
        assertThat(iconOp).isEmpty();
    }
}
