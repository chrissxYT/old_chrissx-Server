package de.chrissx.server.antigrief;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class AntiGriefEvent {

	private AntiGriefEventType type;
	private Entity entity;
	private Location location;
	private String extraInformation;
	
	public AntiGriefEvent(AntiGriefEventType type, Entity entity, Location location, String extra_information) {
		this.type = type;
		this.entity = entity;
		this.location = location;
		this.extraInformation = extra_information;
	}
	
	public String getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(String extraInformation) {
		this.extraInformation = extraInformation;
	}

	public AntiGriefEventType getType() {
		return type;
	}
	public void setType(AntiGriefEventType type) {
		this.type = type;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}