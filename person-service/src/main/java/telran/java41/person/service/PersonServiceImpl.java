package telran.java41.person.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java41.person.dao.PersonRepository;
import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.PersonDto;
import telran.java41.person.model.Person;


@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
	
	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Override
	public boolean addPerson(PersonDto personDto) {
		if(personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		// TODO Auto-generated method stub
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		// TODO Auto-generated method stub
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if(name == null) {
			return null;
		}
		if (updatePersonName(id, name).getName() != null) {
			person.setName(updatePersonName(id, name).getName());
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
		// TODO Auto-generated method stub
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if(addressDto == null) {
			return null;
		}
		if (updatePersonAddress(id, addressDto).getName() != null) {
			person.setAddress(updatePersonAddress(id, addressDto).getAddress());
		}
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

}
