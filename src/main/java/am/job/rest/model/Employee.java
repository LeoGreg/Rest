package am.job.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore// ignores for both sides
    @JsonProperty(access = Access.READ_ONLY)
// they can't write down can only get , write_only can only write can't read for password(so can't be getten by response);
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;


    private double balance;

    @NotBlank
    @JsonProperty(access = WRITE_ONLY)
    private String accessNumber;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    /*
    1 if we consumes json but we get xml for example it'll cause 415 not supported
    2 @Valid uses to secure model restrictions if sth goes wrong it'll cause 40 bad request
     */

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
