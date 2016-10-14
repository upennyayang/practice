package com.yavinci.companies.dropbox;

public class WebLogger {

	int[] arr;
	int size;
	long lastHit = -1;

	public WebLogger(int size) {
		this.size = size;
		this.arr = new int[size];
	}

	public void hit(long ts) {
		int index = (int) ts % size;

		if (ts - lastHit >= size) {
			arr = new int[size];
		}

		if (ts == lastHit) {
			arr[index]++;

		} else {
			arr[index] = 1;
		}

		lastHit = ts;
	}

	public int get(long ts) {
		int sum = 0;

		if (ts - lastHit >= size) {
			return 0;

		} else if (lastHit == ts) {
			sum += sum(arr, 0, size - 1);

		} else if (lastHit < ts) {
			sum += sum(arr, 0, (int) lastHit % size);
			sum += sum(arr, (int) (ts % size), size - 1);

		} else {
			sum += sum(arr, (int) ts % size, (int) lastHit % size);
		}

		return sum;
	}

	public int sum(int[] arr, int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; i++) {
			sum += arr[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		int size = 4;
		WebLogger logger = new WebLogger(size);
		logger.hit(3);
		logger.hit(3);
		logger.hit(5);
		System.out.println(logger.get(5));
	}
}
