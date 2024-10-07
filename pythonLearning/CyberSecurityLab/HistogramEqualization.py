import cv2
import numpy as np

# Load the image
img = cv2.imread('pythonLearning/CyberSecurityLab/shivaji.jpeg')

# Check if the image is loaded correctly
if img is None:
    print("Error: Unable to load image")
else:
    # Convert to grayscale
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # Apply histogram equalization
    enhanced = cv2.equalizeHist(gray)

    # Convert back to color (optional)
    enhanced_color = cv2.cvtColor(enhanced, cv2.COLOR_GRAY2BGR)

    # Display the enhanced image
    cv2.imshow('Enhanced Image', enhanced_color)
    cv2.waitKey(0)
    
    cv2.imwrite('enhanced_shivaji.jpeg', enhanced_color)
    cv2.destroyAllWindows()