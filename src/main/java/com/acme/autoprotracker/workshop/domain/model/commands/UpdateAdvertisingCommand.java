package com.acme.autoprotracker.workshop.domain.model.commands;

public record UpdateAdvertisingCommand(Long id,String comName, String comImage, Long workshopId, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
}
