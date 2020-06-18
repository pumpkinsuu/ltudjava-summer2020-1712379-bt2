package pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 7:31 PM
 * @Description
 */
@Entity
public class GiaoVu {
    private String username;
    private String password;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiaoVu giaoVu = (GiaoVu) o;
        return Objects.equals(username, giaoVu.username) &&
                Objects.equals(password, giaoVu.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
