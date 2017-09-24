/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_isdigit.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: sschafer <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2017/06/02 15:10:39 by sschafer          #+#    #+#             */
/*   Updated: 2017/06/02 15:21:42 by sschafer         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

int		ft_isdigit(int c)
{
	if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5')
		return (1);
	else if (c == '6' || c == '7' || c == '8' || c == '9')
		return (1);
	return (0);
}
