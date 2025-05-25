package com.chatnest.chatnestuserservice.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用 API 响应结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;       // 状态码，0 通常表示成功，非 0 表示异常
    private String message; // 响应提示信息
    private T data;         // 泛型数据内容，可以是对象、布尔值、字符串、列表等

    // ✅ 快速构建成功响应
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(0, message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(0, "操作成功", data);
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(0, "操作成功", null);
    }

    // ✅ 快速构建失败响应
    public static ApiResponse<?> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(-1, message, null);
    }
}
