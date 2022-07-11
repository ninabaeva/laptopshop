package exam.service.impl;

import exam.model.dto.xml.TownDto;
import exam.model.dto.xml.TownRootDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(TOWNS_PATH)));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        TownRootDto townRootDto = this.xmlParser.parseXml(TownRootDto.class, TOWNS_PATH);

        for (TownDto townDto : townRootDto.getTowns()) {
            if (this.validationUtil.isValid(townDto) && this.townRepository.findByName(townDto.getName()) == null) {

                this.townRepository.saveAndFlush(this.modelMapper.map(townDto, Town.class));

                sb.append(String
                        .format("Successfully imported Town %s", townDto.getName()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid town").append(System.lineSeparator());
            }
        }

        return sb.toString();


    }
}
