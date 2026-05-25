package movieticket.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import movieticket.Dto.TheatreRequest;
import movieticket.entity.TheatreEntity;
import movieticket.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theatres")
@Tag(name="Movie__Theatre",description = "API's for Movie theatre ")

public class TheatreController {

    private final TheatreService theatreService;

    public  TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @Operation(summary = "Creating Movie__Theatre",description = "Creating Movie__Theatres of given Api ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Created successfully ",
                    content = @Content(schema = @Schema(implementation = TheatreEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Already created",
                    content = @Content
            )
    })

    @PostMapping
    public ResponseEntity<?> addTheatre(@RequestBody TheatreRequest theatreRequest){
        String theatre=theatreService.addTheatre(theatreRequest);
        if(theatre==null){
            return ResponseEntity.badRequest().body("Theatre is not Found");
        }
        return ResponseEntity.ok("Theatre updated successfully!");
    }
    @Operation(summary = "Get All Theatre Listed",description = "Get All Theatre list ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = TheatreEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })

    @GetMapping
    public ResponseEntity<?>getAllTheatre(){
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }


    @Operation(summary = "Theatre Get id ",description = "retrieves a get id details of theatre ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully Get id ",
                    content = @Content(schema = @Schema(implementation = TheatreEntity.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Id Not Found",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTheatre(@PathVariable Long id){
        return ResponseEntity.ok(theatreService.getTheatre(id));
    }
}
