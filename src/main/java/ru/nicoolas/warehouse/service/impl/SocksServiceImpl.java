package ru.nicoolas.warehouse.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.service.SocksService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {
    private SocksRepository socksRepository;

    public void addSocks(Socks socks) {
        int totalQuantity = 0;
        Optional<Integer> count = socksRepository.getTotalQuantityByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());
        if(count.isPresent()) {
             totalQuantity = count.get();
        }
        Socks socks1 = new Socks();

        socks1.setColor(socks.getColor());
        socks1.setCottonPart(socks.getCottonPart());
        socks1.setQuantity(totalQuantity + socks.getQuantity());
        socksRepository.save(socks1);

    }

    //Optional<Person> optPerson = personRepository.findById(1L);
    //
    //if(optPerson.isPresent()) {
    //	final Person person = optPerson.get();
    //
    //	// остальной ваш код
    //}
//personRepository.findById(1L)
//        .map(User::getFirstName)
//        .ifPresent(
//                firstName -> System.out.println("Длина твоего имени: " + firstName.length())
//        );


    //SELECT COUNT(*) FROM socks WHERE color = :color AND
    //(cotton_part > :cotton_part AND :operation = 'moreThan' OR
    //cotton_part < :cotton_part AND :operation = 'lessThan' OR
    //cotton_part = :cotton_part AND :operation = 'equal')
}
