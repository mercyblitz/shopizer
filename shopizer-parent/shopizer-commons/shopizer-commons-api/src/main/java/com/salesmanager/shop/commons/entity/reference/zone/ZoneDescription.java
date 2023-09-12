package com.salesmanager.shop.commons.entity.reference.zone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesmanager.shop.commons.constants.SchemaConstant;
import com.salesmanager.shop.commons.entity.common.description.Description;
import com.salesmanager.shop.commons.entity.reference.language.Language;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ZONE_DESCRIPTION", uniqueConstraints={
		@UniqueConstraint(columnNames={
			"ZONE_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "zone_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
//@SequenceGenerator(name = "description_gen", sequenceName = "zone_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_SEQUENCE_START)
public class ZoneDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Zone.class)
	@JoinColumn(name = "ZONE_ID", nullable = false)
	private Zone zone;
	
	public ZoneDescription() {
	}
	
	public ZoneDescription(Zone zone, Language language, String name) {
		setZone(zone);
		setLanguage(language);
		setName(name);
	}
	
	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
}
