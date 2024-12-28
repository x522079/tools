package com.example.tools.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.Duration;

public class TimeCalculation {
    private final StringProperty startTime = new SimpleStringProperty();
    private final StringProperty endTime = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private Duration duration;

    public TimeCalculation(String startTime, String endTime, String result, Duration duration) {
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.result.set(result);
        this.duration = duration;
        
        selected.addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                selected.set(newValue);
            }
        });
    }

    public String getStartTime() { return startTime.get(); }
    public String getEndTime() { return endTime.get(); }
    public String getResult() { return result.get(); }
    public boolean isSelected() { return selected.get(); }
    public Duration getDuration() { return duration; }
    
    public void setSelected(boolean value) { selected.set(value); }
    
    public BooleanProperty selectedProperty() { return selected; }
    public StringProperty startTimeProperty() { return startTime; }
    public StringProperty endTimeProperty() { return endTime; }
    public StringProperty resultProperty() { return result; }
} 