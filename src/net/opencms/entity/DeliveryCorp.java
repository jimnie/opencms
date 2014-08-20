
package net.opencms.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "xx_delivery_corp")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_delivery_corp_sequence")
public class DeliveryCorp extends OrderEntity {

	private static final long serialVersionUID = 10595703086045998L;

	private String name;

	private String url;

	private String code;

	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(max = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Length(max = 200)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}