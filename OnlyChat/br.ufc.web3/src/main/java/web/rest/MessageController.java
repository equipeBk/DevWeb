package web.rest;

import java.util.List;
import java.util.Optional;
import web.service.MessageService;
import web.model.Message;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import web.model.User;
import web.repository.MessageRepository;
import web.repository.UserRepository;


@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Outros endpoints do MessageController, como exclusão, atualização, etc.
}


