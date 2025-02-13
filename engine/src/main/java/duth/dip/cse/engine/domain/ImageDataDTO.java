package duth.dip.cse.engine.domain;

import java.awt.image.BufferedImage;

public record ImageDataDTO(
        ColorModel colorModel,
        BufferedImage bufferedImage
) {}
