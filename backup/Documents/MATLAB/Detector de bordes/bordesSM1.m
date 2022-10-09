function edgeImage = bordesSM1(grayImage,threshold)
% Define Kernel for Sobel edge detection
k = single([1 2 1; 0 0 0; -1 -2 -1]);

% Detect Edge
H = conv2(single(grayImage),k, 'same');
V = conv2(single(grayImage),k','same');
E = sqrt(H.*H + V.*V);
edgeImage = uint8((E > threshold) * 255);

end

