package seven.com.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenhaijun on 2017/1/3.
 */

@Entity
@Table(name="tb_myuser")
public class User implements Serializable {

    private static final long serialVersionUID = 7411554675989289001L;

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date udateTime;

    @JSONField(serialize=false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUdateTime() {
        return udateTime;
    }

    public void setUdateTime(Date udateTime) {
        this.udateTime = udateTime;
    }
}
