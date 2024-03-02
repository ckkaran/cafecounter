package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardList {
    @SerializedName("card_terminalid")
    @Expose
    private String card_terminalid;

    @SerializedName("terminal_name")
    @Expose
    private String terminal_name;

    public CardList(String card_terminalid, String terminal_name) {
        this.card_terminalid = card_terminalid;
        this.terminal_name = terminal_name;
    }

    public String getCard_terminalid() {
        return card_terminalid;
    }

    public void setCard_terminalid(String card_terminalid) {
        this.card_terminalid = card_terminalid;
    }

    public String getTerminal_name() {
        return terminal_name;
    }

    public void setTerminal_name(String terminal_name) {
        this.terminal_name = terminal_name;
    }
}
