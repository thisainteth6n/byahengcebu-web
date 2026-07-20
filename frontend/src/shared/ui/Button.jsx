import "./button.css";

function Button({

                    children,

                    variant = "primary",

                    type = "button",

                    onClick,

                    disabled = false,

                    icon,

                    className = "",

                    fullWidth = false

                }) {

    return (

        <button

            type={type}

            disabled={disabled}

            onClick={onClick}

            className={`ui-button ${variant} ${fullWidth ? "full-width" : ""} ${className}`}

        >

            {

                icon && (

                    <span className="button-icon">

                        {icon}

                    </span>

                )

            }

            <span>

                {children}

            </span>

        </button>

    );

}

export default Button;