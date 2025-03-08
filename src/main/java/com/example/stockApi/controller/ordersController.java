package com.example.stockApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController  // 해당 클래스가 REST API의 컨트롤러임을 선언
@RequestMapping("/orders")
public class ordersController {
	// 매수 주문 처리
    @PostMapping("/buy")
    public ResponseEntity<BuyOrderResponse> buyStock(@RequestBody BuyOrderRequest request) {
        // 주문 처리 로직
        BuyOrderResponse response = new BuyOrderResponse();
        
        // 주식 매수 로직 (예시로 성공 처리를 해놓음)
        if (request.getPrice() == 0) {
            // 현재가 주문 로직 처리
            // 예시: 가격을 150,000으로 설정
            request.setPrice(150000);
        }

        // 매수 주문 성공 처리
        response.setResult("success");
        response.setMessage("매수 주문 처리 완료");
        
        // 실제 구현에서 에러가 발생할 수 있기 때문에 예외처리 필요
        // 예: response.setErrorMessage("가격이 잘못되었습니다."); 등

        return ResponseEntity.ok(response);
    }
    
    // 매도 주문 처리 API
    @PostMapping("/sell")
    public ResponseEntity<SellOrderResponse> sellStock(@RequestBody SellOrderRequest request) {
        SellOrderResponse response = new SellOrderResponse();

        // 매도 가격이 0이면 현재가로 설정
        if (request.getPrice() == 0) {
            request.setPrice(150000); // 예시로 현재가 설정
        }

        // 매도 주문 처리 로직 (단순화된 예시)
        // 실제로는 주식 보유량 확인, 가격 확인 등의 로직이 필요
        response.setResult("success");
        response.setMessage("매도 주문 처리 완료");
        response.setErrorMessage("");

        return ResponseEntity.ok(response);
    }
    
 // 매수/매도 체결 결과 조회
    @PostMapping("/status")
    public ResponseEntity<OrderStatusResponse> getOrderStatus(@RequestBody OrderStatusRequest request) {
        OrderStatusResponse response = new OrderStatusResponse();

        // 주문 ID가 유효한지 확인
        if (request.getOrderId() <= 0) {
            response.setResult("error");
            response.setMessage("주문 ID 존재하지 않음");
            response.setErrorMessage("잘못된 주문 ID");
            response.setStatus(0); // 상태 0은 유효하지 않은 주문 ID
            return ResponseEntity.ok(response);
        }

        // 주문 ID에 대한 처리 (여기서는 임의로 데이터를 설정)
        // 실제 구현에서는 DB 조회나 외부 서비스와 연동하여 처리
        response.setOrderId(request.getOrderId());
        response.setResult("success");
        response.setMessage("매수/매도 체결 결과 조회 성공");
        response.setErrorMessage(""); // 에러가 없으면 빈 문자열
        response.setStatus(1); // 체결완료 상태
        response.setStockId("TSLA"); // 예시로 주식 ID는 TSLA
        response.setQuantity(10); // 체결 수량 예시
        response.setPrice(150000); // 체결 가격 예시

        return ResponseEntity.ok(response);
    }
}

class BuyOrderRequest {
    private String userId;
    private String stockId;
    private int quantity;
    private int price;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class BuyOrderResponse {
    private String result; 
    private String message; 
    private String errorMessage; 

    // Getters and Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

class SellOrderRequest {
    private String userId;  // 사용자 ID
    private String stockId; // 주식 ID
    private int quantity;   // 매도할 주식 수량
    private int price;      // 매도 가격

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class SellOrderResponse {
    private String result;        // 성공: "success", 실패: "error"
    private String message;       // 성공 시 메시지
    private String errorMessage;  // 실패 시 에러 메시지

    // Getters and Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

class OrderStatusRequest {
    private int orderId; // 주문 ID
    private String userId;	// 사용자 ID

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

class OrderStatusResponse {
    private int orderId;        // 주문 ID
    private String result;      // 성공: "success", 실패: "error"
    private String message;     // 성공 시 메시지
    private String errorMessage;// 실패 시 에러 메시지
    private int status;         // 주문 상태: 1: 체결완료, 2: 미체결상태, 3: 취소된 주문
    private String stockId;     // 주식 ID
    private int quantity;       // 체결 수량
    private int price;          // 체결 가격

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

