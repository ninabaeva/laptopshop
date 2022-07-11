package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.json.LaptopDto;
import exam.model.entity.Laptop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;


@Service
public class LaptopServiceImpl implements LaptopService {

    private static final String LAPTOPS_PATH = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(LAPTOPS_PATH)));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();

        LaptopDto[] laptopDtos = this.gson.fromJson(this.readLaptopsFileContent(), LaptopDto[].class);

        for (LaptopDto laptopDto : laptopDtos) {


            if (this.validationUtil.isValid(laptopDto)
                    && laptopRepository.findByMacAddress(laptopDto.getMacAddress()) == null
                    && laptopDto.getWarrantyType() != null) {

                Laptop laptop = this.modelMapper.map(laptopDto, Laptop.class);

                laptop.setShop(this.shopRepository.findByName(laptopDto.getShop().getName()));

//                switch (laptopDto.getWarrantyType()) {
//                    case "BASIC" -> laptop.setWarrantyType(WarrantyType.BASIC);
//                    case "PREMIUM" -> laptop.setWarrantyType(WarrantyType.PREMIUM);
//                    case "LIFETIME" -> laptop.setWarrantyType(WarrantyType.LIFETIME);
//                }

                this.laptopRepository.saveAndFlush(laptop);

                sb.append(String.format("Successfully import laptop - %s - %.2f - %d - %d", laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage()))
                        .append(System.lineSeparator());

            } else {
                sb.append("Invalid laptop").append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();

        Set<Laptop> laptops = this.laptopRepository.export();

        for (Laptop laptop : laptops) {
            sb.append(String.format("Laptop - %s", laptop.getMacAddress())).append(System.lineSeparator());
            sb.append(String.format("*Cpu speed - %.2f", laptop.getCpuSpeed())).append(System.lineSeparator());
            sb.append(String.format("**Ram - %d", laptop.getRam())).append(System.lineSeparator());
            sb.append(String.format("***Storage - %d", laptop.getStorage())).append(System.lineSeparator());
            sb.append(String.format("****Price - %.2f", laptop.getPrice())).append(System.lineSeparator());
            sb.append(String.format("#Shop name - %s", laptop.getShop().getName())).append(System.lineSeparator());
            sb.append(String.format("##Town - %s", laptop.getShop().getTown().getName())).append(System.lineSeparator());
        }

        return sb.toString();

    }
}
