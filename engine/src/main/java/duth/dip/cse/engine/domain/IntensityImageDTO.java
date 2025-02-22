package duth.dip.cse.engine.domain;

import java.awt.image.BufferedImage;

public record IntensityImageDTO(
        int[] values,
        BufferedImage image
) {}
