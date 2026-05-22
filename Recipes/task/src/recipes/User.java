package recipes;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class User {

    @Id
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Column
    @Size(min = 8)
    @NotBlank
    @NotNull
    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipeList = new ArrayList<>();


}
