package hr.tvz.miholic.hardwareapp.Controllers;

import hr.tvz.miholic.hardwareapp.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }


    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping(params = "code")
    public HardwareDTO getHardwareByCode(@RequestParam final String code){
        return hardwareService.findByCode(code);
    }
    @GetMapping(params = "type")
    public List<HardwareDTO> getHardwareByType(@RequestParam final String type){
        return hardwareService.findByType(type);
    }

    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }





}
