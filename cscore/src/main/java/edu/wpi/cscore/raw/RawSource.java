/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.cscore.raw;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.ImageSource;
import edu.wpi.cscore.VideoMode;

/**
 * A source for user code to provide video frames as raw bytes.
 *
 * <p>This is a complex API, most cases should use CvSource.
 */
public class RawSource extends ImageSource {
  /**
   * Create a raw frame source.
   *
   * @param name Source name (arbitrary unique identifier)
   * @param mode Video mode being generated
   */
  public RawSource(String name, VideoMode mode) {
    super(CameraServerJNI.createRawSource(name,
        mode.pixelFormat.getValue(),
        mode.width, mode.height,
        mode.fps));
  }

  /**
   * Create a raw frame source.
   *
   * @param name Source name (arbitrary unique identifier)
   * @param pixelFormat Pixel format
   * @param width width
   * @param height height
   * @param fps fps
   */
  public RawSource(String name, VideoMode.PixelFormat pixelFormat, int width, int height, int fps) {
    super(CameraServerJNI.createRawSource(name,
        pixelFormat.getValue(),
        width, height,
        fps));
  }

  /**
   * Put a raw image and notify sinks.
   *
   * @param image raw frame image
   */
  protected void putFrame(RawFrame image) {
    CameraServerJNI.putRawSourceFrame(m_handle, image);
  }

  /**
   * Put a raw image and notify sinks.
   *
   * @param data raw frame data pointer
   * @param width frame width
   * @param height frame height
   * @param pixelFormat pixel format
   * @param totalData length of data in total
   */
  protected void putFrame(long data, int width, int height, int pixelFormat, int totalData) {
    CameraServerJNI.putRawSourceFrame(m_handle, data, width, height, pixelFormat, totalData);
  }

  /**
   * Put a raw image and notify sinks.
   *
   * @param data raw frame data pointer
   * @param width frame width
   * @param height frame height
   * @param pixelFormat pixel format
   * @param totalData length of data in total
   */
  protected void putFrame(long data, int width, int height, VideoMode.PixelFormat pixelFormat,
                          int totalData) {
    CameraServerJNI.putRawSourceFrame(m_handle, data, width, height, pixelFormat.getValue(),
                                      totalData);
  }
}
