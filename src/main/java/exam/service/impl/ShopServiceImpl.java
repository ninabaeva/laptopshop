package exam.service.impl;

import exam.model.dto.xml.ShopDto;
import exam.model.dto.xml.ShopRootDto;
import exam.model.entity.Shop;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
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
public class ShopServiceImpl implements ShopService {

    private static final String SHOPS_PATH = "src/main/resources/files/xml/shops.xml";

    private final TownRepository townRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public ShopServiceImpl(TownRepository townRepository, ShopRepository shopRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(SHOPS_PATH)));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        ShopRootDto shopRootDto = this.xmlParser.parseXml(ShopRootDto.class, SHOPS_PATH);

        for (ShopDto shopDto : shopRootDto.getShops()) {
            if (this.validationUtil.isValid(shopDto)
                    && this.shopRepository.findByName(shopDto.getName()) == null
                    && this.townRepository.findByName(shopDto.getTown().getName()) != null) {



                Shop shop = this.modelMapper.map(shopDto, Shop.class);
                shop.setTown(townRepository.findByName(shopDto.getTown().getName()));




                this.shopRepository.saveAndFlush(shop);

                sb.append(String
                        .format("Successfully imported Shop %s - %s", shop.getName(), shop.getIncome()))
                        .append(System.lineSeparator());
            } else {
                sb.append("Invalid shop").append(System.lineSeparator());
            }
        }

        return sb.toString();

    }
}
