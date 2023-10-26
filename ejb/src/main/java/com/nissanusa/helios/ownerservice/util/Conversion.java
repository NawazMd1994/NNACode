package com.nissanusa.helios.ownerservice.util;

import org.apache.log4j.Logger;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Conversion implements OwnerConstants {

	private static final Logger LOG = Logger.getLogger(Utility.class);
	private static final String input = "E:/desktop/angularjs.pdf";

	public Conversion() {
		// Default Constructor
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		File file = new File(input);
		byte[] bArray = convertPDFToByteArray();
		byte[] bArray1 = readBytesFromFile(file);
		byte[] bArray2 = convertImageToByteArray();

		LOG.info("bArray2" + Arrays.toString(bArray2));
		LOG.info("bArray" + Arrays.toString(bArray));
		LOG.info("bArray1" + Arrays.toString(bArray1));
	}

	public static byte[] convertPDFToByteArray() {

		FileInputStream fileInputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			File file = new File(input);

			int size = (int) file.length();

			if (size > Integer.MAX_VALUE) {

				LOG.info("File is to larger");
			}

			byte[] buffer = new byte[(int) file.length()];

			fileInputStream = new FileInputStream(file);

			baos = new ByteArrayOutputStream();

			int bytesRead;

			while ((bytesRead = fileInputStream.read(buffer)) != -1) {

				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {

			LOG.error("FileNotFoundException in convertPDFToByteArray", e);

		} catch (IOException e) {
			LOG.error("IOException in convertPDFToByteArray", e);

		} finally {

			if (fileInputStream != null) {

				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOG.error("IOException in finally", e);
				}
			}
		}

		return baos.toByteArray();

	}

	public static byte[] convertImageToByteArray()
			throws FileNotFoundException, IOException {

		File file = new File("E:/pdf/imagesj.jpg");
		FileInputStream fis = new FileInputStream(file);
		double bytes = file.length();
		LOG.info(bytes);
		LOG.info("File Size: " + String.format("%.2f", bytes / (1024 * 1024))
				+ "mb");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {

				bos.write(buf, 0, readNum);

			}
		} catch (IOException ex) {
			LOG.info(ex.getMessage());
		} finally {

			if (fis != null) {

				try {
					fis.close();
				} catch (IOException e) {
					LOG.error("IOException in finally", e);
				}
			}
		}

		return bos.toByteArray();

	}

	public static BufferedImage createRGBImage(byte[] imageInByte, int width,
			int height) {

		DataBufferByte buffer = new DataBufferByte(imageInByte,
				imageInByte.length);
		ColorModel cm = new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB),
				new int[] { 8, 8, 8 }, false, false, Transparency.OPAQUE,
				DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, Raster.createInterleavedRaster(buffer,
				width, height, width * 3, 3, new int[] { 0, 1, 2 }, null),
				false, null);
	}

	public static byte[] readBytesFromFile(File file) throws IOException {

		file = new File(input);
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		// You cannot create an array using a long type.
		// It needs to be an int type.
		// Before converting to an int type, check
		// to ensure that file is not larger than Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			LOG.info("File is to larger");
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			LOG.info("Could not completely read file");
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

}
