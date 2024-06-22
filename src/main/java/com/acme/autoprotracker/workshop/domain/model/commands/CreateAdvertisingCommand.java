package com.acme.autoprotracker.workshop.domain.model.commands;

public record CreateAdvertisingCommand(String comName, String comImage, Long workshopId, String imageUrl, String slogan, String priceMsg, String disMsg, String repairMsg) {
}
