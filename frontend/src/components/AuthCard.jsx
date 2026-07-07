import { motion } from "framer-motion";

function AuthCard({ children }) {

    return (

        <motion.div

            className="auth-card"

            initial={{
                opacity:0,
                y:40,
                scale:.95
            }}

            animate={{
                opacity:1,
                y:0,
                scale:1
            }}

            transition={{
                duration:.6
            }}

        >

            {children}

        </motion.div>

    );

}

export default AuthCard;