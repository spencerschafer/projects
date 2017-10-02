/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   st.c                                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: gvan-roo <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2017/08/16 13:48:01 by gvan-roo          #+#    #+#             */
/*   Updated: 2017/09/14 08:43:07 by hstander         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "../headers/asm.h"

/*
**	Swops the bits of an int from little endian to big endian
**	and prints to file the correct number of bytes
*/

static int		swop_int_bits(int fd, int i, char c)
{
	unsigned char	byte_swop;

	if (c == '%')
	{
		byte_swop = (i >> 8) & 0xFF;
		write(fd, (void *)&byte_swop, 1);
		byte_swop = 0x00;
		byte_swop = i & 0xFF;
		write(fd, (void *)&byte_swop, 1);
	}
	else if (c == 'r')
	{
		byte_swop = i & 0xFF;
		write(fd, (void *)&byte_swop, 1);
	}
	else
	{
		i = (i >> 8 & 0x00FF) | (i << 8 & 0xFF00);
		write(fd, (void *)&i, 2);
	}
	return (i);
}

/*
**	Receives the file descriptor and ld's parameters as arguments.
**	Processes the parameters to get the int value of them, and
**	prints the first parameter's 4 bytes to file, and the second's
**	parameter's (a register) last byte to the file
*/

static void		create_param(t_args *ag, t_prog *lst)
{
	int				arg_param;
	char			*sub;

	sub = ft_strsub(lst->data[1], 1, (ft_strlen(lst->data[1]) - 1));
	arg_param = ft_checknum(sub, ag);
	free(sub);
	if (arg_param < 1 || arg_param > 16)
		my_error(1, ag);
	arg_param = swop_int_bits(ag->fd, arg_param, lst->data[1][0]);
	if (lst->data[2][0] == 'r')
	{
		sub = ft_strsub(lst->data[2], 1, (ft_strlen(lst->data[2]) - 1));
		arg_param = ft_checknum(sub, ag);
		free(sub);
		if (arg_param < 1 || arg_param > 16)
			my_error(1, ag);
	}
	else if (lst->data[2][0] == ':')
		arg_param = arg_label(2, ag, lst);
	else
		arg_param = ft_checknum(lst->data[2], ag);
	arg_param = swop_int_bits(ag->fd, arg_param, lst->data[2][0]);
}

/*
**	Function receives file descriptor and ld's parameters as arguments.
**	Processes the parameters into an argument code byte, and writes the
**	acb to the file indicated by fd.
*/

static void		create_acb(t_args *ag, char *arg1, char *arg2)
{
	unsigned char	hex;

	if (arg1[0] != 'r')
		my_error(2, ag);
	hex = 0b01000000;
	if (arg2[0] == '%')
		my_error(2, ag);
	else if (arg2[0] == 'r')
		hex = hex | 0b00010000;
	else
		hex = hex | 0b00110000;
	if (write(ag->fd, (void *)&hex, 1) < 0)
		my_error(3, ag);
}

/*
**	Main function handling st opcode. Writes st's opcode to
**	the file indicated by fd, and call relevant functions to
**	write the argument coding byte and parameter values to file.
*/

void			ft_st(t_args *ag, t_prog *lst)
{
	unsigned char	hex;

	hex = 0x03;
	if (write(ag->fd, (void *)&hex, 1) < 0)
		my_error(3, ag);
	if (ft_arrlen(lst->data) != 3)
		my_error(4, ag);
	create_acb(ag, lst->data[1], lst->data[2]);
	create_param(ag, lst);
}
