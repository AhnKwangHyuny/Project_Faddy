package faddy.backend.user.domain;

import faddy.backend.image.domain.Image;
import jakarta.persistence.*;

@Entity
@Table(name = "profileImage")
public class ProfileImage extends Image {


//    @OneToOne(mappedBy = "profileImage" )
//    private User user;

}
