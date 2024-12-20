package com.example.depositrateservice.rest.annotation.CheckUniqueDepositid;

import com.example.depositrateservice.enums.DocumentsFRONT;
import com.example.depositrateservice.rest.resources.DocumentRequest;
import com.example.depositrateservice.rest.resources.DocumentResponse;
import com.example.depositrateservice.service.DepositValidationService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class DepositRestController2 {

    private final DepositValidationService depositValidationService;


    @GetMapping(value = "/getDocument", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResponse> createDeposit(@RequestBody DocumentRequest documentRequest) {
        return Try.of(() -> processRunner(documentRequest))
                .recover(RuntimeException.class, err ->
                        ResponseEntity.badRequest().body(buildResponse(err.getMessage())))
                .recover(IllegalArgumentException.class, err ->
                        ResponseEntity.badRequest().body(buildResponse(err.getMessage())))
                .recover(IllegalCallerException.class, err ->
                        ResponseEntity.badRequest().body(buildResponse(err.getMessage())))
                .get();
    }

    private static DocumentResponse buildResponse(String message) {
        return DocumentResponse.builder()
                .errorMessage(message)
                .status("FAIL")
                .build();
    }

    private ResponseEntity<DocumentResponse> processRunner(DocumentRequest documentRequest) {
        if (documentRequest.getRequestId() == null || documentRequest.getRequestId().isEmpty()) {
            throw new RuntimeException("RequestId is null");
        }

        if (documentRequest.getDocumentId() == null) {
            throw new IllegalArgumentException("DocumentId is null");
        }

        String documentLink = Arrays.stream(DocumentsFRONT.values())
                .map(DocumentsFRONT::getDocumentId)
                .filter(documentId -> documentId.equals(documentRequest.getDocumentId()))
                .map(this::getDocumentLink)
                .findFirst()
                .orElseThrow(() -> new IllegalCallerException("Document link not found"));

        String name = Arrays.stream(DocumentsFRONT.values())
                .filter(doc -> doc.getDocumentId().equals(documentRequest.getDocumentId()))
                .map(DocumentsFRONT::getDocumentName)
                .findFirst()
                .orElseThrow(() -> new IllegalCallerException("Document name not found"));

        return ResponseEntity.ok(
                DocumentResponse.builder()
                        .documentName(name)
                        .documentLink(documentLink)
                        .status("SUCCESS")
                        .build()
        );
    }


    private String getDocumentLink(Integer documentId) {
        if (documentId == 207) {
            return "dkfsldfksdlaf000000000000";
        }
        if (documentId == 111) {
            return "czxcsx4324*&7asjalsa";
        }
        if (documentId == 453) {
            return "xzxczcz231xsasdx34543tsx";
        }
        if (documentId == 566) {
            return "dascc,lldasikgofihnerui";
        }
        if (documentId == 5342) {
            return "dpapallllxkasdjasoidjidsa;";
        }
        return "";

    }
}
