package com.pen.backend.singletons;

import com.pen.backend.models.Points;
import org.springframework.stereotype.Component;

@Component
public class PointHolder {

    public static Points points = new Points();

}
