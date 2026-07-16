import "../styles/button.css";

function Button({
                    children,
                    type = "submit",
                    disabled = false,
                    onClick
                }) {
    return (
        <button
            type={type}
            className="primary-button"
            disabled={disabled}
            onClick={onClick}
        >
            {children}
        </button>
    );
}

export default Button;