package hiber.model;

import javax.persistence.*;

@Entity
@Table (name = "car")
public class Car {

    @OneToOne (mappedBy = "car")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Car() {
    }

    public Car (String model, int series) {
        this.model = model;
        this.series = series;
    }

    @Column
    private String model;
    @Column
    private int series;

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }
}
