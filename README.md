# ColorSpaceEditor

## Development Environment Setup

To set up the development environment, ensure you have the following:

##### Java 21 (OpenJDK or Oracle JDK)

Install Oracle/Open JDK 21 and add the Java's bin directory to your system PATH.

##### Gradle

Install Gradle (at least version 8.1) and add the Gradle's bin directory to your system PATH.

##### WiX Toolset

Install the latest version of the WiX Toolset and add the WiX Toolset bin directory to your system PATH.

##### IDE or Text Editor

Use an IDE like IntelliJ IDEA, Eclipse, or any text editor of your choice.




## Building the Project

##### Follow these steps to build the project:

1. Clone the Project by opening a command prompt and run: `git clone https://github.com/gkakavas/ColorSpaceEditor.git`

2. Navigate to the Project Directory: `cd ColorSpaceEditor`

3. Build the ProjectRun the following command: `gradle clean jpackage`

#### NOTE: After a successful build, the executable file will be produced in the: 
> ColorSpaceEditor/build/distribution directory.



## Usage

##### The application enables the user to:

- **Load** any image file and convert it to a supported color space using the color model chooser.

- **Save** the modified image by pressing the save button. A tag of the color space is added to the file name so that the application can read the file again in the converted color model.

- **Capture** an image using the image capture button and process it as any other loaded picture.

- **Clear** the image using the clear button to load or capture a new image.

- **Adjust** the intensity of an RGB image by modifying color space channels using sliders.

- **View** all changes in real time using the built-in image previewer, which displays the loaded image in RGB format.

##### Supported File Types

- BMP
- TIFF 
- PNG
- JPEG / JPG
- WEBP

##### Supported Color Spaces

- GRAYSCALE
- RGB
- RGBA
- HSV
- LUV
- LAB
- YCbCr

#### Additional Notes
If using an external webcam, it may not work properly due to potential issues with OpenCV video drivers handling external cameras.
