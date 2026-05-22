package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;


@Data
@Entity
@Table
public class Recipe {

    private static long id = 1;

    @Id
    @JsonIgnore
    private long recipe_id;

    @Column
    private LocalDateTime date;

    @Column
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;

    @Column
    @NotEmpty
    @NotNull
    @NotBlank
    private String description;

    @Column
    @NotEmpty
    @NotNull
    @NotBlank
    private String category;

    @Column
    @Size(min = 1)
    @NotEmpty
    @NotNull
    private String[] ingredients;

    @Column
    @Size(min = 1)
    @NotEmpty
    @NotNull
    private String[] directions;

    @ManyToOne
    @JoinColumn(name = "email")
    @JsonIgnore
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", category: '" + category + '\'' +
                ", date: " + date.getYear() + '-' + date.getMonthValue() + '-' + date.getDayOfMonth() +
                ", description: '" + description + '\'' +
                ", ingredients: " + Arrays.toString(ingredients) +
                ", directions: " + Arrays.toString(directions) +
                '}';
    }

    public Recipe(){
        this.recipe_id = id;
        id++;
        this.date = LocalDateTime.now();
    }

}
