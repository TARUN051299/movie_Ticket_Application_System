package movieticket.controller;


import movieticket.Dto.TheatreRequest;

import movieticket.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theatres")

public class TheatreController {

    private final TheatreService theatreService;

    public  TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    public ResponseEntity<?> addTheatre(@RequestBody TheatreRequest theatreRequest){
        String theatre=theatreService.addTheatre(theatreRequest);
        if(theatre==null){
            return ResponseEntity.badRequest().body("Theatre is not Found");
        }
        return ResponseEntity.ok("Theatre updated successfully!");
    }

    @GetMapping
    public ResponseEntity<?>getAllTheatre(){
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTheatre(@PathVariable Long id){
        return ResponseEntity.ok(theatreService.getTheatre(id));
    }
}
