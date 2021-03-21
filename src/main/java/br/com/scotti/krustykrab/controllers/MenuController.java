package br.com.scotti.krustykrab.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.scotti.krustykrab.entities.Menu;
import br.com.scotti.krustykrab.repositories.MenuRepository;

@RestController
public class MenuController
{

    @Autowired
    private MenuRepository _menuRepository;

    @RequestMapping(value="/menu", method=RequestMethod.GET)
    public ResponseEntity<List<Menu>> findAll() {
        return new ResponseEntity<List<Menu>>(_menuRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/menu/{id}", method=RequestMethod.GET)
    public ResponseEntity<Menu> findById(@PathVariable(value="id") long id) {
        Optional<Menu> menu = _menuRepository.findById(id);

        if (menu.isPresent()) {
            return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/menu", method=RequestMethod.POST)
    public ResponseEntity<Menu> create(@Valid @RequestBody Menu menu) {
        return new ResponseEntity<Menu>(_menuRepository.save(menu), HttpStatus.OK);
    }

    @RequestMapping(value="/menu/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Menu> update(@PathVariable(value="id") long id, @Valid @RequestBody Menu newMenu) {
        Optional<Menu> oldMenu = _menuRepository.findById(id);

        if (oldMenu.isPresent()) {
            Menu menu = oldMenu.get();
            menu.setName(newMenu.getName());
            _menuRepository.save(menu);

            return new ResponseEntity<Menu>(menu, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/menu/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Menu> delete(@PathVariable(value="id") long id) {
        Optional<Menu> menu = _menuRepository.findById(id);

        if (menu.isPresent()) {
            _menuRepository.delete(menu.get());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
