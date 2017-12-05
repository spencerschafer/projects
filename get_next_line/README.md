# To run this program type the following:
cd libft
make
cd ..
gcc -Wall -Werror -Wextra main.c get_next_line.c -Ilibft/includes libft/libft.a -o gnl
./gnl tests/1.txt

# To remove created files type the following:
cd libft
make fclean
cd ..
rm gnl

# Note:
This folder contains a slightly modified version of get_next_line and libft
to demonstrate the project that was submitted for evaluation at WeThinkCode_.

To select different tests, replace 1.txt with the corresponding file you would
like to use-e.g. ./gnl tests/42.txt
