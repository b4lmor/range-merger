package cft.shift.lab.b4lmor.rangemerger.controller;

import cft.shift.lab.b4lmor.rangemerger.DTO.impl.IntervalDTO;
import cft.shift.lab.b4lmor.rangemerger.service.IntervalService;
import cft.shift.lab.b4lmor.rangemerger.utils.Kind;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1/intervals")
@Validated
@Tag(name = "api.intervals.tag.name", description = "api.intervals.tag.description")
public class IntervalController {
    @Autowired
    private IntervalService intervalService;
    @GetMapping("min")
    @Operation(summary = "api.intervals.get-min.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.intervals.get-min.api-responses.200.description"),
            @ApiResponse(responseCode = "400", description = "api.intervals.get-min.api-responses.400.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "api.intervals.get-min.api-responses.404.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    public ResponseEntity<IntervalDTO<?>> getMinInterval(
            @Parameter(
                    name = "api.schemas.kind.name",
                    description = "api.schemas.kind.description",
                    example = "digits",
                    required = true)
            @RequestParam(required = true, name = "kind")
            String rawKind) {
        Kind kind = Kind.getKindByString(rawKind);
        IntervalDTO<?> minIntervalDTO = intervalService.getMinInterval(kind);
        return new ResponseEntity<>(
                minIntervalDTO,
                HttpStatus.OK
        );
    }
    @PostMapping("merge")
    @Operation(summary = "api.intervals.merge.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.intervals.merge.api-responses.200.description"),
            @ApiResponse(responseCode = "400", description = "api.intervals.merge.api-responses.400.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    public ResponseEntity<Void> mergeIntervals(
            @Parameter(
                    name =  "api.schemas.kind.name",
                    description  = "api.schemas.kind.description",
                    example = "letters",
                    required = true)
            @RequestParam(required = true, name = "kind")
            String rawKind,
            @RequestBody
            @NotEmpty(message = "api.error-message.no-intervals")
            List<@Valid IntervalDTO<?>> intervalDTOs) {
        Kind kind = Kind.getKindByString(rawKind);
        intervalService.mergeIntervals(kind, intervalDTOs);
        return ResponseEntity.ok().build();
    }

}
