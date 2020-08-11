package ar.edu.itba.paw.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class AddProductForm {
    private MultipartFile img;
    private String description;
    private double price;
}
